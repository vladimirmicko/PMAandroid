package data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.randjelovic.vladimir.myapplication.common.Utility;

import java.io.Serializable;


public class Statistics implements Serializable {

	private int $testId;


	private int _01__totalNumberOfResultsForTest=0;

	private String _02__NumberOfExaminees$$ = "";
	private int _03__numberOfMen=0;
	private int _04__numberOfWomen=0;
	private int _05__numberOfMen12_30=0;
	private int _06__numberOfMen31_50=0;
	private int _07__numberOfMen51_75=0;
	private int _08__numberOfWomen12_30=0;
	private int _09__numberOfWomen31_50=0;
	private int _10__numberOfWomen51_75=0;

	private String _11__ResultsByGender$$ = "";
	private int _12__numberOfMenPositive=0;
	private int _13__numberOfMenNegative=0;
	private int _14__numberOfWomenPositive=0;
	private int _15__numberOfWomenNegative=0;

	private String _16__ResultsByAgeForMen$$ = "";
	private int _17__numberOfMenPositive12_30=0;
	private int _18__numberOfMenNegative12_30=0;
	private int _19__numberOfMenPositive31_50=0;
	private int _20__numberOfMenNegative31_50=0;
	private int _21__numberOfMenPositive51_75=0;
	private int _22__numberOfMenNegative51_75=0;
	private double _23__percentMenPositive=0;
	private double _24__percentMenNegative=0;

	private String _25__ResultsByAgeForWomen$$ = "";
	private int _26_numberOfWomenPositive12_30=0;
	private int _27__numberOfWomenNegative12_30=0;
	private int _28__numberOfWomenPositive31_50=0;
	private int _29__numberOfWomenNegative31_50=0;
	private int _30__numberOfWomenPositive51_75=0;
	private int _31__numberOfWomenNegative51_75=0;
	private double _32__percentWomenPositive=0;
	private double _33__percentWomenNegative=0;

	private String _34__ResponseTime$$ = "";
	private double _35__averageResponseTime=0;
	private double _36__averageResponseTimeMen=0;
	private double _37__averageResponseTimeWomen=0;

	private String _38__ResponseTimeByAge$$S = "";
	private double _39__averageResponseTimeMen12_30=0;
	private double _40__averageResponseTimeMen31_50=0;
	private double _41__averageResponseTimeMen51_75=0;
	private double _42__averageResponseTimeWomen12_30=0;
	private double _43__averageResponseTimeWomen31_50=0;
	private double _44__averageResponseTimeWomen51_75=0;



	public Statistics() {
	}


	//	-------------------------------------------------------------------------- Calculated getters
	public double getPercentMenPositive(){
		if (this._03__numberOfMen>0){
			this._23__percentMenPositive=1.0*this._12__numberOfMenPositive/this._03__numberOfMen;
		}
		else{
			this._23__percentMenPositive=0;
		}
		return this._23__percentMenPositive;
	}

	public void setPercentMenPositive(double percentMenPositive) {
		this._23__percentMenPositive = percentMenPositive;
	}


	public double getPercentMenNegative(){
		if (this._03__numberOfMen>0){
			this._24__percentMenNegative=1.0*this._13__numberOfMenNegative/this._03__numberOfMen;
		}
		else{
			this._24__percentMenNegative=0;
		}
		return this._24__percentMenNegative;
	}


	public void setPercentMenNegative(double percentMenNegative) {
		this._24__percentMenNegative = percentMenNegative;
	}


	public double getPercentWomenPositive(){
		if (this._04__numberOfWomen>0){
			this._32__percentWomenPositive=1.0*this._14__numberOfWomenPositive/this._04__numberOfWomen;
		}
		else{
			this._32__percentWomenPositive=0;
		}
		return this._32__percentWomenPositive;
	}

	public void setPercentWomenPositive(double percentWomenPositive) {
		this._32__percentWomenPositive = percentWomenPositive;
	}


	public double getPercentWomenNegative(){
		if (this._04__numberOfWomen>0){
			this._33__percentWomenNegative=1.0*this._15__numberOfWomenNegative/this._04__numberOfWomen;
		}
		else{
			this._33__percentWomenNegative=0;
		}
		return this._33__percentWomenNegative;
	}

	public void setPercentWomenNegative(double percentWomenNegative) {
		this._33__percentWomenNegative = percentWomenNegative;
	}
//	-------------------------------------------------------------------------- End of Calculated getters


	public int get$testId() {
		return $testId;
	}


	public int get_01__totalNumberOfResultsForTest() {
		return _01__totalNumberOfResultsForTest;
	}


	public String get_02__NumberOfExaminees$$() {
		return _02__NumberOfExaminees$$;
	}


	public int get_03__numberOfMen() {
		return _03__numberOfMen;
	}


	public int get_04__numberOfWomen() {
		return _04__numberOfWomen;
	}


	public int get_05__numberOfMen12_30() {
		return _05__numberOfMen12_30;
	}


	public int get_06__numberOfMen31_50() {
		return _06__numberOfMen31_50;
	}


	public int get_07__numberOfMen51_75() {
		return _07__numberOfMen51_75;
	}


	public int get_08__numberOfWomen12_30() {
		return _08__numberOfWomen12_30;
	}


	public int get_09__numberOfWomen31_50() {
		return _09__numberOfWomen31_50;
	}


	public int get_10__numberOfWomen51_75() {
		return _10__numberOfWomen51_75;
	}


	public String get_11__ResultsByGender$$() {
		return _11__ResultsByGender$$;
	}


	public int get_12__numberOfMenPositive() {
		return _12__numberOfMenPositive;
	}


	public int get_13__numberOfMenNegative() {
		return _13__numberOfMenNegative;
	}


	public int get_14__numberOfWomenPositive() {
		return _14__numberOfWomenPositive;
	}


	public int get_15__numberOfWomenNegative() {
		return _15__numberOfWomenNegative;
	}


	public String get_16__ResultsByAgeForMen$$() {
		return _16__ResultsByAgeForMen$$;
	}


	public int get_17__numberOfMenPositive12_30() {
		return _17__numberOfMenPositive12_30;
	}


	public int get_18__numberOfMenNegative12_30() {
		return _18__numberOfMenNegative12_30;
	}


	public int get_19__numberOfMenPositive31_50() {
		return _19__numberOfMenPositive31_50;
	}


	public int get_20__numberOfMenNegative31_50() {
		return _20__numberOfMenNegative31_50;
	}


	public int get_21__numberOfMenPositive51_75() {
		return _21__numberOfMenPositive51_75;
	}


	public int get_22__numberOfMenNegative51_75() {
		return _22__numberOfMenNegative51_75;
	}


	public double get_23__percentMenPositive() {
		return _23__percentMenPositive;
	}


	public double get_24__percentMenNegative() {
		return _24__percentMenNegative;
	}


	public String get_25__ResultsByAgeForWomen$$() {
		return _25__ResultsByAgeForWomen$$;
	}


	public int get_26_numberOfWomenPositive12_30() {
		return _26_numberOfWomenPositive12_30;
	}


	public int get_27__numberOfWomenNegative12_30() {
		return _27__numberOfWomenNegative12_30;
	}


	public int get_28__numberOfWomenPositive31_50() {
		return _28__numberOfWomenPositive31_50;
	}


	public int get_29__numberOfWomenNegative31_50() {
		return _29__numberOfWomenNegative31_50;
	}


	public int get_30__numberOfWomenPositive51_75() {
		return _30__numberOfWomenPositive51_75;
	}


	public int get_31__numberOfWomenNegative51_75() {
		return _31__numberOfWomenNegative51_75;
	}


	public double get_32__percentWomenPositive() {
		return _32__percentWomenPositive;
	}


	public double get_33__percentWomenNegative() {
		return _33__percentWomenNegative;
	}


	public String get_34__ResponseTime$$() {
		return _34__ResponseTime$$;
	}


	public double get_35__averageResponseTime() {
		return _35__averageResponseTime;
	}


	public double get_36__averageResponseTimeMen() {
		return _36__averageResponseTimeMen;
	}


	public double get_37__averageResponseTimeWomen() {
		return _37__averageResponseTimeWomen;
	}


	public String get_38__ResponseTimeByAge$$S() {
		return _38__ResponseTimeByAge$$S;
	}


	public double get_39__averageResponseTimeMen12_30() {
		return _39__averageResponseTimeMen12_30;
	}


	public double get_40__averageResponseTimeMen31_50() {
		return _40__averageResponseTimeMen31_50;
	}


	public double get_41__averageResponseTimeMen51_75() {
		return _41__averageResponseTimeMen51_75;
	}


	public double get_42__averageResponseTimeWomen12_30() {
		return _42__averageResponseTimeWomen12_30;
	}


	public double get_43__averageResponseTimeWomen31_50() {
		return _43__averageResponseTimeWomen31_50;
	}


	public double get_44__averageResponseTimeWomen51_75() {
		return _44__averageResponseTimeWomen51_75;
	}


	public void set$testId(int $testId) {
		this.$testId = $testId;
	}


	public void set_01__totalNumberOfResultsForTest(int _01__totalNumberOfResultsForTest) {
		this._01__totalNumberOfResultsForTest = _01__totalNumberOfResultsForTest;
	}


	public void set_02__NumberOfExaminees$$(String _02__NumberOfExaminees$$) {
		this._02__NumberOfExaminees$$ = _02__NumberOfExaminees$$;
	}


	public void set_03__numberOfMen(int _03__numberOfMen) {
		this._03__numberOfMen = _03__numberOfMen;
	}


	public void set_04__numberOfWomen(int _04__numberOfWomen) {
		this._04__numberOfWomen = _04__numberOfWomen;
	}


	public void set_05__numberOfMen12_30(int _05__numberOfMen12_30) {
		this._05__numberOfMen12_30 = _05__numberOfMen12_30;
	}


	public void set_06__numberOfMen31_50(int _06__numberOfMen31_50) {
		this._06__numberOfMen31_50 = _06__numberOfMen31_50;
	}


	public void set_07__numberOfMen51_75(int _07__numberOfMen51_75) {
		this._07__numberOfMen51_75 = _07__numberOfMen51_75;
	}


	public void set_08__numberOfWomen12_30(int _08__numberOfWomen12_30) {
		this._08__numberOfWomen12_30 = _08__numberOfWomen12_30;
	}


	public void set_09__numberOfWomen31_50(int _09__numberOfWomen31_50) {
		this._09__numberOfWomen31_50 = _09__numberOfWomen31_50;
	}


	public void set_10__numberOfWomen51_75(int _10__numberOfWomen51_75) {
		this._10__numberOfWomen51_75 = _10__numberOfWomen51_75;
	}


	public void set_11__ResultsByGender$$(String _11__ResultsByGender$$) {
		this._11__ResultsByGender$$ = _11__ResultsByGender$$;
	}


	public void set_12__numberOfMenPositive(int _12__numberOfMenPositive) {
		this._12__numberOfMenPositive = _12__numberOfMenPositive;
	}


	public void set_13__numberOfMenNegative(int _13__numberOfMenNegative) {
		this._13__numberOfMenNegative = _13__numberOfMenNegative;
	}


	public void set_14__numberOfWomenPositive(int _14__numberOfWomenPositive) {
		this._14__numberOfWomenPositive = _14__numberOfWomenPositive;
	}


	public void set_15__numberOfWomenNegative(int _15__numberOfWomenNegative) {
		this._15__numberOfWomenNegative = _15__numberOfWomenNegative;
	}


	public void set_16__ResultsByAgeForMen$$(String _16__ResultsByAgeForMen$$) {
		this._16__ResultsByAgeForMen$$ = _16__ResultsByAgeForMen$$;
	}


	public void set_17__numberOfMenPositive12_30(int _17__numberOfMenPositive12_30) {
		this._17__numberOfMenPositive12_30 = _17__numberOfMenPositive12_30;
	}


	public void set_18__numberOfMenNegative12_30(int _18__numberOfMenNegative12_30) {
		this._18__numberOfMenNegative12_30 = _18__numberOfMenNegative12_30;
	}


	public void set_19__numberOfMenPositive31_50(int _19__numberOfMenPositive31_50) {
		this._19__numberOfMenPositive31_50 = _19__numberOfMenPositive31_50;
	}


	public void set_20__numberOfMenNegative31_50(int _20__numberOfMenNegative31_50) {
		this._20__numberOfMenNegative31_50 = _20__numberOfMenNegative31_50;
	}


	public void set_21__numberOfMenPositive51_75(int _21__numberOfMenPositive51_75) {
		this._21__numberOfMenPositive51_75 = _21__numberOfMenPositive51_75;
	}


	public void set_22__numberOfMenNegative51_75(int _22__numberOfMenNegative51_75) {
		this._22__numberOfMenNegative51_75 = _22__numberOfMenNegative51_75;
	}


	public void set_23__percentMenPositive(double _23__percentMenPositive) {
		this._23__percentMenPositive = _23__percentMenPositive;
	}


	public void set_24__percentMenNegative(double _24__percentMenNegative) {
		this._24__percentMenNegative = _24__percentMenNegative;
	}


	public void set_25__ResultsByAgeForWomen$$(String _25__ResultsByAgeForWomen$$) {
		this._25__ResultsByAgeForWomen$$ = _25__ResultsByAgeForWomen$$;
	}


	public void set_26_numberOfWomenPositive12_30(int _26_numberOfWomenPositive12_30) {
		this._26_numberOfWomenPositive12_30 = _26_numberOfWomenPositive12_30;
	}


	public void set_27__numberOfWomenNegative12_30(int _27__numberOfWomenNegative12_30) {
		this._27__numberOfWomenNegative12_30 = _27__numberOfWomenNegative12_30;
	}


	public void set_28__numberOfWomenPositive31_50(int _28__numberOfWomenPositive31_50) {
		this._28__numberOfWomenPositive31_50 = _28__numberOfWomenPositive31_50;
	}


	public void set_29__numberOfWomenNegative31_50(int _29__numberOfWomenNegative31_50) {
		this._29__numberOfWomenNegative31_50 = _29__numberOfWomenNegative31_50;
	}


	public void set_30__numberOfWomenPositive51_75(int _30__numberOfWomenPositive51_75) {
		this._30__numberOfWomenPositive51_75 = _30__numberOfWomenPositive51_75;
	}


	public void set_31__numberOfWomenNegative51_75(int _31__numberOfWomenNegative51_75) {
		this._31__numberOfWomenNegative51_75 = _31__numberOfWomenNegative51_75;
	}


	public void set_32__percentWomenPositive(double _32__percentWomenPositive) {
		this._32__percentWomenPositive = _32__percentWomenPositive;
	}


	public void set_33__percentWomenNegative(double _33__percentWomenNegative) {
		this._33__percentWomenNegative = _33__percentWomenNegative;
	}


	public void set_34__ResponseTime$$(String _34__ResponseTime$$) {
		this._34__ResponseTime$$ = _34__ResponseTime$$;
	}


	public void set_35__averageResponseTime(double _35__averageResponseTime) {
		this._35__averageResponseTime = _35__averageResponseTime;
	}


	public void set_36__averageResponseTimeMen(double _36__averageResponseTimeMen) {
		this._36__averageResponseTimeMen = _36__averageResponseTimeMen;
	}


	public void set_37__averageResponseTimeWomen(double _37__averageResponseTimeWomen) {
		this._37__averageResponseTimeWomen = _37__averageResponseTimeWomen;
	}


	public void set_38__ResponseTimeByAge$$S(String _38__ResponseTimeByAge$$S) {
		this._38__ResponseTimeByAge$$S = _38__ResponseTimeByAge$$S;
	}


	public void set_39__averageResponseTimeMen12_30(double _39__averageResponseTimeMen12_30) {
		this._39__averageResponseTimeMen12_30 = _39__averageResponseTimeMen12_30;
	}


	public void set_40__averageResponseTimeMen31_50(double _40__averageResponseTimeMen31_50) {
		this._40__averageResponseTimeMen31_50 = _40__averageResponseTimeMen31_50;
	}


	public void set_41__averageResponseTimeMen51_75(double _41__averageResponseTimeMen51_75) {
		this._41__averageResponseTimeMen51_75 = _41__averageResponseTimeMen51_75;
	}


	public void set_42__averageResponseTimeWomen12_30(double _42__averageResponseTimeWomen12_30) {
		this._42__averageResponseTimeWomen12_30 = _42__averageResponseTimeWomen12_30;
	}


	public void set_43__averageResponseTimeWomen31_50(double _43__averageResponseTimeWomen31_50) {
		this._43__averageResponseTimeWomen31_50 = _43__averageResponseTimeWomen31_50;
	}


	public void set_44__averageResponseTimeWomen51_75(double _44__averageResponseTimeWomen51_75) {
		this._44__averageResponseTimeWomen51_75 = _44__averageResponseTimeWomen51_75;
	}


}

