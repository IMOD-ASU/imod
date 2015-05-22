package imod

class SyllabusSettings {

    String description

    static hasMany = [
        prefs: SyllabusPrefs
    ]

    static constraints = {
    }
}
