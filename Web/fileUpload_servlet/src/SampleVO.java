
public class SampleVO {
	private int num;
	private String sample;
	private String imageFileName;

	public SampleVO(int num, String sample, String imageFileName) {
		this.num = num;
		this.sample = sample;
		this.imageFileName = imageFileName;
	}

	public SampleVO() {
	}

	public int getNum() {
		return this.num;
	}

	public String getImageFileName() {
		return this.imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getSample() {
		return this.sample;
	}

	public void setSample(String sample) {
		this.sample = sample;
	}

}
