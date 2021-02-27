package br.com.cursojsf;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.bind.DatatypeConverter;

import com.google.gson.Gson;

import br.com.dao.DaoGeneric;
import br.com.entidades.Cidades;
import br.com.entidades.Estados;
import br.com.entidades.Pessoa;
import br.com.jpautil.JPAUtil;
import br.com.repository.IDaoPessoa;


@javax.faces.view.ViewScoped
@Named(value="pessoaBean")
public class PessoaBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Pessoa pessoa = new Pessoa();
	
	@Inject
	private DaoGeneric<Pessoa> daoGeneric ;
	
	private List<Pessoa> pessoas = new ArrayList<Pessoa>();
	
	@Inject
	private IDaoPessoa iDaoPessoa ;
	
	@Inject
	private JPAUtil jpaUtil;
	
	private List<SelectItem> estados ;
	
	private List<SelectItem>cidades;
	
	private Part arquivofoto; /* PART - CLASSE AUXILIAR PARA FAZER UPLOAD*/
	
		
	/*public String salvar() {
		daoGeneric.salvar(pessoa);
		pessoa = new Pessoa();
		return " ";
	}
*/
	
	public String salvar() throws IOException {//pega dados da tela e conforme o metodo de retorno injeta os dados da entidade salva 
		
		/* PROCESSAR IMAGEM */
		byte[] imagemByte = getByte(arquivofoto.getInputStream()); /* IMAGEM NO TAMANHO ORIGINAL EM BYTES*/
		pessoa.setFotoIconBase64Original(imagemByte);
		
		/* TRANSFORMAR EM BUFFERIMAGE */
		BufferedImage bufImage = ImageIO.read(new ByteArrayInputStream(imagemByte));
		/* PEGA O TIPO DA IMAGEM */
		int type = bufImage.getType()  == 0 ? BufferedImage.TYPE_INT_ARGB : bufImage.getType();
		
		int largura = 200;
		int altura = 200;
		
		/* CRIAR A MINIATURA*/
		BufferedImage resizedImage = new BufferedImage(largura, altura, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(bufImage, 0, 0, largura, altura, null);
		g.dispose();
		
		/*ESCREVER NOVAMENTE A IMAGEM EM TAMANHO MENOR*/
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		String extensao = arquivofoto.getContentType().split("\\/")[1]; /* pega a extensão quebrando em array com o split pegando a posição 1  - image/png*/
		ImageIO.write(resizedImage, extensao, baos);
		
		String mniImagem = "data:" + arquivofoto.getContentType() + ";base64, " + DatatypeConverter.printBase64Binary(baos.toByteArray());
		
		/* PROCESSAR IMAGEM */
		
		pessoa.setFotoIconBase64(mniImagem);
		pessoa.setExtensao(extensao);
		
		pessoa = daoGeneric.merge(pessoa);
		carregarPessoas();
		mostrarMsg("Cadastrado com sucesso!");
		return "";
	}
	
	public void registraLog() {
		
		System.out.println("método registraLog"); //testando açao do usuario chamando um método pelo ActionListner
		
	}
	public void mudancaValor(ValueChangeEvent evento) { // metodo é chamado quando o formulario é enviado
		
		System.out.println("Valor antigo: " + evento.getOldValue());
		System.out.println("Valor novo: " + evento.getNewValue());		
	}
	
	
	public String novo() {
		pessoa = new Pessoa();
		return " ";
	}
	
	public String deletar() {
		daoGeneric.deletePorId(pessoa);
		pessoa = new Pessoa();
		carregarPessoas();
		mostrarMsg("Removido com sucesso!");
		return"";
	}
	
	@PostConstruct
	public void carregarPessoas() {
		pessoas = daoGeneric.getListaEntidades(pessoa);
	}
	
	public String logar() {
		String url = "";
		Pessoa pessoaUser = iDaoPessoa.consultarUsuario(pessoa.getLogin(), pessoa.getSenha());
		
		if (pessoaUser != null ) {//achou usuario
			
			//usuario precisa ser adcionado na sessão para não ser bloqueado o acesso pelo filtro - usuarioLogado
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext externalContext = context.getExternalContext();
			externalContext.getSessionMap().put("usuarioLogado", pessoaUser);
			url = "/primeirapagina.jsf";
		} else {
			url = "/index.jsf";
		}
		return url;
	}
	
	public boolean permiteAcesso(String acesso) {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		Pessoa pessoaUser = (Pessoa) externalContext.getSessionMap().get("usuarioLogado");
		
		return pessoaUser.getPerfilUser().equals(acesso);
	}
	
	private void mostrarMsg(String msg) {
		
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(msg);
		context.addMessage(null, message);
		
	}
	
	public String limpar() {
		//Executa algum processo antes de limpar
		pessoa = new Pessoa();
		
		return "";
	}
	
	public void pesquisaCep(AjaxBehaviorEvent event) {
		try {
			URL url = new URL("https://viacep.com.br/ws/"+pessoa.getCep()+"/json/");
			URLConnection connection = url.openConnection();
			InputStream is = connection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			
			String cep = "";
			StringBuilder jsonCep = new StringBuilder();
			
			while((cep = br.readLine()) != null) {
				jsonCep.append(cep);
			}
			
			Pessoa gsonAux = new Gson().fromJson(jsonCep.toString(), Pessoa.class);
			
			pessoa.setCep(gsonAux.getCep());
			pessoa.setLogradouro(gsonAux.getLogradouro());
			pessoa.setComplemento(gsonAux.getComplemento());
			pessoa.setBairro(gsonAux.getBairro());
			pessoa.setLocalidade(gsonAux.getLocalidade());
			pessoa.setUf(gsonAux.getUf());
			
		}catch (Exception e) {
			e.printStackTrace();
			mostrarMsg("Erro ao consultar o CEP!");
		}
		
	}
	
	public String deslogar() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		externalContext.getSessionMap().remove("usuarioLogado");
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) context.getCurrentInstance().getExternalContext().getRequest();
		httpServletRequest.getSession().invalidate();
		
		return "index.jsf";
	}
	
	public List<SelectItem> getEstados() {
		
		estados = iDaoPessoa.listaEstados();
		
		return estados;
	}
	
	public void carregaCidades(AjaxBehaviorEvent event) {
		/*PEGA OBJETO INTEIRO SELECIONADO NO COMBOBOX*/
		Estados estado = (Estados) ((HtmlSelectOneMenu)event.getSource()).getValue();
			
			if (estado != null) {
				pessoa.setEstados(estado);
				
				List<Cidades> cidades = jpaUtil.getEntityManager().createQuery("from Cidades where estados.id =  " + estado.getId()).getResultList();
				
				List<SelectItem> selectItensCidade = new ArrayList<SelectItem>();
				
				for (Cidades cidade : cidades) {
					
					selectItensCidade.add(new SelectItem(cidade, cidade.getNome()));
				}
				setCidades(selectItensCidade);
			}
			
		}
	
	public String editar() {
		if (pessoa.getCidades() != null) {
			Estados estado = pessoa.getCidades().getEstados();
			pessoa.setEstados(estado);
			
			List<Cidades> cidades = jpaUtil.getEntityManager().createQuery("from Cidades where estados.id = " + estado.getId()).getResultList();
			
			List<SelectItem> selectItemCidade = new ArrayList<SelectItem>();
			
			for(Cidades cidade : cidades) {
				selectItemCidade.add(new SelectItem(cidade, cidade.getNome()));	
				
			
			}
			setCidades(selectItemCidade);
		}
		return "";
	}
	
	public void download() throws IOException {
		
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String  fileDownloadId= params.get("fileDownloadId");
		
		Pessoa pessoa = daoGeneric.consultar(Pessoa.class, fileDownloadId);
		
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		
		response.addHeader("Content-Disposition", "attachment; filename=download." + pessoa.getExtensao() );
		response.setContentType("application/octet-stream");
		response.setContentLength(pessoa.getFotoIconBase64Original().length);
		response.getOutputStream().write(pessoa.getFotoIconBase64Original());
		response.getOutputStream().flush();
		FacesContext.getCurrentInstance().responseComplete();
		
	}
		
	
	
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public List<Pessoa> getPessoas() {
		return pessoas;
	}
	public List<SelectItem> getCidades() {
		return cidades;
	}
	public void setCidades(List<SelectItem> cidades) {
		this.cidades = cidades;
	}
	public Part getArquivofoto() {
		return arquivofoto;
	}
	public void setArquivofoto(Part arquivofoto) {
		this.arquivofoto = arquivofoto;
	}
	
	public JPAUtil getJpaUtil() {
		return jpaUtil;
	}

	public void setJpaUtil(JPAUtil jpaUtil) {
		this.jpaUtil = jpaUtil;
	}

	/* METODO QUE CONVERTE INPUTSTREAM PARA ARRAY DE BYTES*/
	private byte[] getByte (InputStream is) throws IOException {
		
		int len;
		int size =1024;
		byte[] buf = null;
		
		if (is instanceof ByteArrayInputStream) {
			size = is.available();
			buf = new byte[size];
			len = is.read(buf, 0, size);
		}else {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			buf = new byte[size];
			
			while ((len = is.read(buf, 0, size)) != -1) {
				
				bos.write(buf, 0, len);
				
			}
			buf = bos.toByteArray();
		
	}
	return buf;
		
}
	
	
}
