package data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;


public class Statistics implements Serializable {

	private int testId;
	private int totalNumberOfResultsForTest=0;

	private int numberOfMen=0;
	private int numberOfWomen=0;
	private int numberOfMen_30=0;
	private int numberOfMen31_50=0;
	private int numberOfMen51_=0;
	private int numberOfWomen_30=0;
	private int numberOfWomen31_50=0;
	private int numberOfWomen51_=0;

	private int numberOfMenPositive=0;
	private int numberOfMenNegative=0;
	private int numberOfWomenPositive=0;
	private int numberOfWomenNegative=0;

	private int numberOfMenPositive_30=0;
	private int numberOfMenNegative_30=0;
	private int numberOfMenPositive31_50=0;
	private int numberOfMenNegative31_50=0;
	private int numberOfMenPositive51_=0;
	private int numberOfMenNegative51_=0;

	private int numberOfWomenPositive_30=0;
	private int numberOfWomenNegative_30=0;
	private int numberOfWomenPositive31_50=0;
	private int numberOfWomenNegative31_50=0;
	private int numberOfWomenPositive51_=0;
	private int numberOfWomenNegative51_=0;

	private double averageResponseTime=0;
	private double averageResponseTimeMen=0;
	private double averageResponseTimeWomen=0;

	private double averageResponseTimeMen_30=0;
	private double averageResponseTimeMen31_50=0;
	private double averageResponseTimeMen51_=0;
	private double averageResponseTimeWomen_30=0;
	private double averageResponseTimeWomen31_50=0;
	private double averageResponseTimeWomen51_=0;



	public Statistics() {
	}


	public int getTestId() {
		return testId;
	}


	public void setTestId(int testId) {
		this.testId = testId;
	}



	public int getNumberOfMen() {
		return numberOfMen;
	}


	public void setNumberOfMen(int numberOfMen) {
		this.numberOfMen = numberOfMen;
	}


	public int getNumberOfWomen() {
		return numberOfWomen;
	}


	public void setNumberOfWomen(int numberOfWomen) {
		this.numberOfWomen = numberOfWomen;
	}


	public int getNumberOfMen_30() {
		return numberOfMen_30;
	}


	public void setNumberOfMen_30(int numberOfMen_30) {
		this.numberOfMen_30 = numberOfMen_30;
	}


	public int getNumberOfMen31_50() {
		return numberOfMen31_50;
	}


	public void setNumberOfMen31_50(int numberOfMen31_50) {
		this.numberOfMen31_50 = numberOfMen31_50;
	}


	public int getNumberOfMen51_() {
		return numberOfMen51_;
	}


	public void setNumberOfMen51_(int numberOfMen51_) {
		this.numberOfMen51_ = numberOfMen51_;
	}


	public int getNumberOfWomen_30() {
		return numberOfWomen_30;
	}


	public void setNumberOfWomen_30(int numberOfWomen_30) {
		this.numberOfWomen_30 = numberOfWomen_30;
	}


	public int getNumberOfWomen31_50() {
		return numberOfWomen31_50;
	}


	public void setNumberOfWomen31_50(int numberOfWomen31_50) {
		this.numberOfWomen31_50 = numberOfWomen31_50;
	}


	public int getNumberOfWomen51_() {
		return numberOfWomen51_;
	}


	public void setNumberOfWomen51_(int numberOfWomen51_) {
		this.numberOfWomen51_ = numberOfWomen51_;
	}


	public int getNumberOfMenPositive() {
		return numberOfMenPositive;
	}


	public void setNumberOfMenPositive(int numberOfMenPositive) {
		this.numberOfMenPositive = numberOfMenPositive;
	}


	public int getNumberOfMenNegative() {
		return numberOfMenNegative;
	}


	public void setNumberOfMenNegative(int numberOfMenNegative) {
		this.numberOfMenNegative = numberOfMenNegative;
	}


	public int getNumberOfWomenPositive() {
		return numberOfWomenPositive;
	}


	public void setNumberOfWomenPositive(int numberOfWomenPositive) {
		this.numberOfWomenPositive = numberOfWomenPositive;
	}


	public int getNumberOfWomenNegative() {
		return numberOfWomenNegative;
	}


	public void setNumberOfWomenNegative(int numberOfWomenNegative) {
		this.numberOfWomenNegative = numberOfWomenNegative;
	}


	public int getNumberOfMenPositive_30() {
		return numberOfMenPositive_30;
	}


	public void setNumberOfMenPositive_30(int numberOfMenPositive_30) {
		this.numberOfMenPositive_30 = numberOfMenPositive_30;
	}


	public int getNumberOfMenNegative_30() {
		return numberOfMenNegative_30;
	}


	public void setNumberOfMenNegative_30(int numberOfMenNegative_30) {
		this.numberOfMenNegative_30 = numberOfMenNegative_30;
	}


	public int getNumberOfMenPositive31_50() {
		return numberOfMenPositive31_50;
	}


	public void setNumberOfMenPositive31_50(int numberOfMenPositive31_50) {
		this.numberOfMenPositive31_50 = numberOfMenPositive31_50;
	}


	public int getNumberOfMenNegative31_50() {
		return numberOfMenNegative31_50;
	}


	public void setNumberOfMenNegative31_50(int numberOfMenNegative31_50) {
		this.numberOfMenNegative31_50 = numberOfMenNegative31_50;
	}


	public int getNumberOfMenPositive51_() {
		return numberOfMenPositive51_;
	}


	public void setNumberOfMenPositive51_(int numberOfMenPositive51_) {
		this.numberOfMenPositive51_ = numberOfMenPositive51_;
	}


	public int getNumberOfMenNegative51_() {
		return numberOfMenNegative51_;
	}


	public void setNumberOfMenNegative51_(int numberOfMenNegative51_) {
		this.numberOfMenNegative51_ = numberOfMenNegative51_;
	}



	public int getNumberOfWomenPositive_30() {
		return numberOfWomenPositive_30;
	}


	public void setNumberOfWomenPositive_30(int numberOfWomenPositive_30) {
		this.numberOfWomenPositive_30 = numberOfWomenPositive_30;
	}


	public int getNumberOfWomenNegative_30() {
		return numberOfWomenNegative_30;
	}


	public void setNumberOfWomenNegative_30(int numberOfWomenNegative_30) {
		this.numberOfWomenNegative_30 = numberOfWomenNegative_30;
	}


	public int getNumberOfWomenPositive31_50() {
		return numberOfWomenPositive31_50;
	}


	public void setNumberOfWomenPositive31_50(int numberOfWomenPositive31_50) {
		this.numberOfWomenPositive31_50 = numberOfWomenPositive31_50;
	}


	public int getNumberOfWomenNegative31_50() {
		return numberOfWomenNegative31_50;
	}


	public void setNumberOfWomenNegative31_50(int numberOfWomenNegative31_50) {
		this.numberOfWomenNegative31_50 = numberOfWomenNegative31_50;
	}


	public int getNumberOfWomenPositive51_() {
		return numberOfWomenPositive51_;
	}


	public void setNumberOfWomenPositive51_(int numberOfWomenPositive51_) {
		this.numberOfWomenPositive51_ = numberOfWomenPositive51_;
	}


	public int getNumberOfWomenNegative51_() {
		return numberOfWomenNegative51_;
	}


	public void setNumberOfWomenNegative51_(int numberOfWomenNegative51_) {
		this.numberOfWomenNegative51_ = numberOfWomenNegative51_;
	}


	public double getAverageResponseTime() {
		return averageResponseTime;
	}


	public void setAverageResponseTime(double averageResponseTime) {
		this.averageResponseTime = averageResponseTime;
	}


	public double getAverageResponseTimeMen() {
		return averageResponseTimeMen;
	}


	public void setAverageResponseTimeMen(double averageResponseTimeMen) {
		this.averageResponseTimeMen = averageResponseTimeMen;
	}


	public double getAverageResponseTimeWomen() {
		return averageResponseTimeWomen;
	}


	public void setAverageResponseTimeWomen(double averageResponseTimeWomen) {
		this.averageResponseTimeWomen = averageResponseTimeWomen;
	}


	public double getAverageResponseTimeMen_30() {
		return averageResponseTimeMen_30;
	}


	public void setAverageResponseTimeMen_30(double averageResponseTimeMen_30) {
		this.averageResponseTimeMen_30 = averageResponseTimeMen_30;
	}


	public double getAverageResponseTimeMen31_50() {
		return averageResponseTimeMen31_50;
	}


	public void setAverageResponseTimeMen31_50(double averageResponseTimeMen31_50) {
		this.averageResponseTimeMen31_50 = averageResponseTimeMen31_50;
	}


	public double getAverageResponseTimeMen51_() {
		return averageResponseTimeMen51_;
	}


	public void setAverageResponseTimeMen51_(double averageResponseTimeMen51_) {
		this.averageResponseTimeMen51_ = averageResponseTimeMen51_;
	}


	public double getAverageResponseTimeWomen_30() {
		return averageResponseTimeWomen_30;
	}


	public void setAverageResponseTimeWomen_30(double averageResponseTimeWomen_30) {
		this.averageResponseTimeWomen_30 = averageResponseTimeWomen_30;
	}


	public double getAverageResponseTimeWomen31_50() {
		return averageResponseTimeWomen31_50;
	}


	public void setAverageResponseTimeWomen31_50(double averageResponseTimeWomen31_50) {
		this.averageResponseTimeWomen31_50 = averageResponseTimeWomen31_50;
	}


	public double getAverageResponseTimeWomen51_() {
		return averageResponseTimeWomen51_;
	}


	public void setAverageResponseTimeWomen51_(double averageResponseTimeWomen51_) {
		this.averageResponseTimeWomen51_ = averageResponseTimeWomen51_;
	}


	public int getTotalNumberOfResultsForTest() {
		return totalNumberOfResultsForTest;
	}


	public void setTotalNumberOfResultsForTest(int totalNumberOfResultsForTest) {
		this.totalNumberOfResultsForTest = totalNumberOfResultsForTest;
	}

	@JsonIgnore
	public double getPercentMenPositive(){
		double percentMenPositive=0;
		if (numberOfMenPositive+numberOfMenNegative>0){
			percentMenPositive=numberOfMenPositive/(numberOfMenPositive+numberOfMenNegative);
		}
		else{
			percentMenPositive=0;
		}
		return percentMenPositive;
	}


	@JsonIgnore
	public double getPercentMenNegative(){
		double percentMenNegative=0;
		if (numberOfMenPositive+numberOfMenNegative>0){
			percentMenNegative=numberOfMenNegative/(numberOfMenPositive+numberOfMenNegative);
		}
		else{
			percentMenNegative=0;
		}
		return percentMenNegative;
	}

	@JsonIgnore
	public double getPercentWomenPositive(){
		double percentWomenPositive=0;
		if (numberOfWomenPositive+numberOfWomenNegative>0){
			percentWomenPositive=numberOfWomenPositive/(numberOfWomenPositive+numberOfWomenNegative);
		}
		else{
			percentWomenPositive=0;
		}
		return percentWomenPositive;
	}

	@JsonIgnore
	public double getPercentWomenNegative(){
		double percentWomenNegative=0;
		if (numberOfWomenPositive+numberOfWomenNegative>0){
			percentWomenNegative=numberOfWomenNegative/(numberOfWomenPositive+numberOfWomenNegative);
		}
		else{
			percentWomenNegative=0;
		}
		return percentWomenNegative;
	}

	

}

