package entity;

public class Info {
	private String code;
	private String text;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Info(String code, String text) {
		super();
		this.code = code;
		this.text = text;
	}

	@Override
	public String toString() {
		return "Info [code=" + code + ", text=" + text + "]";
	}

}
