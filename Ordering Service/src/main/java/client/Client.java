package client;

import lombok.Data;
import portofolio.Portofolio;


@Data
public class Client {
	int age;
	String name;
	String address;

	String portofolioList;
	
	public Client() {}
	
	public Client(int age, String name, String address, String portofolios) {
		
		this.age = age;
		this.name = name;
		this.address = address ;
		this.portofolioList = portofolios;
	}

	public Client(int age, String name, String address){
		this(age,name,address,"");
	}

	public void addPortofolio(Portofolio portofolio){
		portofolioList+=portofolio;
	}

	@Override
	public String toString() {
		return "Client [age=" + age + ", name=" + name +", address="+address  + ", portofolio: "  + portofolioList.toString()+ "]" ;
	}


}
