package imod

class SyllabusPrefs {

    Integer sortNumber
    Boolean selected

    static constraints = {
    }
    
    static belongsTo = [syllabus: SyllabusSettings]
}
