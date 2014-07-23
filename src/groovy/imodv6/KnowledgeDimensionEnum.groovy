package imodv6

public enum KnowledgeDimensionEnum {
	FACTUAL('Factual'),
	CONCEPTUAL('Conceptual'),
	PROCEDUAL('Procedural'),
	METACOGNITIVE('Metacognitive')
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
}
