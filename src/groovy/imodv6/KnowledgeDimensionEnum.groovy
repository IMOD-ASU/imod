package imodv6

public enum KnowledgeDimensionEnum {
	FACTUAL('Factual'),
	CONCEPTUAL('Conceptual'),
	PROCEDUAL('Procedural'),
	METACOGNITIVE('Metacognitive'),
	String value
	KnowledgeDimensionEnum(String value){
		this.value=value
	}
	
	@Override
	String toString(){
		value
	}
	String getKey(){
		name()
	}
}
