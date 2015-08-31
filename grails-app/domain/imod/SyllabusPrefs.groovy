package imod

class SyllabusPrefs {

	String hideSectionsList

	Imod imod

	static belongsTo = [
		Imod
	]

    static constraints = {
    	hideSectionsList	nullable:	true
    }
}
