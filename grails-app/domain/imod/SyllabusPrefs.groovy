package imod

class SyllabusPrefs {

	String hideSectionsList
	String sortIdList

	Imod imod

	static belongsTo = [
		Imod
	]

	static constraints = {
		hideSectionsList	nullable:	true
		sortIdList			nullable:	true
	}

	String toString() {
		sortIdList
	}
}
