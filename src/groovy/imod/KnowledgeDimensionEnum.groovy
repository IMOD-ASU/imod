package imod

/**
 * This stores the list of the Knowledge Dimension categories
 */
public enum KnowledgeDimensionEnum {
	CONCEPTUAL('Conceptually','Conceptualdimension related tooltip message'),
	FACTUAL('Factual','Factual dimension related tooltip message'),
	METACOGNITIVE('Metacognitive','Metacognitive dimension related tooltip message'),
	PROCEDURAL('Procedural','Procedural dimension related tooltip message')

	private final String value
	private final String info

	KnowledgeDimensionEnum(String value, String info){
		this.value=value
		this.info=info
	}

	public String value(){return value}
	public String info(){return info}

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

	String getInfo(){
		info
	}
}
