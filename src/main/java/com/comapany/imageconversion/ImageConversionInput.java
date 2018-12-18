package com.comapany.imageconversion;

public class ImageConversionInput {
	
	private String fileName;
	private String fileType;
	private char barCodeFlag;
	private String imageData;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public char getBarCodeFlag() {
		return barCodeFlag;
	}
	public void setBarCodeFlag(char barCodeFlag) {
		this.barCodeFlag = barCodeFlag;
	}
	public String getImageData() {
		return imageData;
	}
	public void setImageData(String imageData) {
		this.imageData = imageData;
	}
	@Override
	public String toString() {
		return "ImageConversionInput [fileName=" + fileName + ", fileType=" + fileType + ", barCodeFlag=" + barCodeFlag
				+ ", imageData=" + imageData + "]";
	}
}
