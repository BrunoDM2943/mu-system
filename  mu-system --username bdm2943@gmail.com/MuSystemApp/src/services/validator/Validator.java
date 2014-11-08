package services.validator;

public class Validator {
	
	public static boolean isEmpty(String e){
		return "".equalsIgnoreCase(e) || e == null || "null".equals(e);
	}
	
	public static boolean isZero(Number e){
		return e.shortValue() == 0 || e == null;
	}	

	public static String nlv(String e){
		if(isEmpty(e))
			return null;
		return e;
	}
	
	public static Number nlv(Number e){
		if(isZero(e))
			return 0;
		return e;
	}
	
}
