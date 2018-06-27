package tw.noah.spring.boot.api.example.model;

/**
 * 制式 JsonModel用的 Message
 */
public enum JsonMsg {
	Success("SUCESS"),
	Failure("FAILURE"),
	Error("ERROR"),
	Null(null);
	
	private String desc;
	
	JsonMsg(String desc){
		this.desc = desc;
	}
	public String getDesc(){
		return desc;
	}
}
