package imodv6

public enum KnowledgeDimensionEnum {
	CONCEPTUAL('Conceptual'),
	FACTUAL('Factual'),
	METACOGNITIVE('Metacognitive'),
	PROCEDURAL('Procedural')
	private final String value
	KnowledgeDimensionEnum(String value){
		this.value=value
	}
	
	public String value(){return value}
	@Override
	String toString(){
		value
	}
	String getKey(){
		name()
	}
	String getValue(){
		value
	}
}
