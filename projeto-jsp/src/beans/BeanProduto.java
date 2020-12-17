package beans;

public class BeanProduto {
	
	private long id_prod;
	private String desc_prod;
	private Float qtd_prod;
	private Float preco_prod;
	private long categoria_id;
	
	
	public long getId_prod() {
		return id_prod;
	}
	public void setId_prod(long id_prod) {
		this.id_prod = id_prod;
	}
	public String getDesc_prod() {
		return desc_prod;
	}
	public void setDesc_prod(String desc_prod) {
		this.desc_prod = desc_prod;
	}
	public Float getQtd_prod() {
		return qtd_prod;
	}
	public void setQtd_prod(Float qtd_prod) {
		this.qtd_prod = qtd_prod;
	}
	public Float getPreco_prod() {
		return preco_prod;
	}
	public void setPreco_prod(Float preco_prod) {
		this.preco_prod = preco_prod;
	}
	
	public String getValorTexto() {
		return String.valueOf(preco_prod).replaceAll("\\.", ",");
	}
	
	public String getQtdTexto() {
		return String.valueOf(qtd_prod).replaceAll("\\.", ",");
	}
	
	public long getCategoria_id() {
		return categoria_id;
	}
	
	public void setCategoria_id(long categoria_id) {
		this.categoria_id = categoria_id;
	}
	
}
