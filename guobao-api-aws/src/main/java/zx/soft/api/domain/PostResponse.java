package zx.soft.api.domain;

import java.io.Serializable;
import java.util.List;

public class PostResponse implements Serializable {

	private static final long serialVersionUID = -5183178204181065266L;

	private int errorCode;
	private List<String> errorMessage;

	public PostResponse(int errorCode, List<String> errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public List<String> getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(List<String> errorMessage) {
		this.errorMessage = errorMessage;
	}

}
