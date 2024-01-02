package entite;

public class Post {

	private int id;
	private String title;
	private String body;
	
	public Post(int id, String title, String body) {
		super();
		this.id = id;
		this.title = title;
		this.body = body;
	}
	
	

	public Post() {
		
	}


	////////////////////// GETTER //////////////////////
	
	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getBody() {
		return body;
	}
	
	////////////////////// SETTER //////////////////////

	public void setId(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	
}
