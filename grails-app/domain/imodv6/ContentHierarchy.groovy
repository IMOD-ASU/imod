package imodv6

class ContentHierarchy {
    Content parentContent
    Content leafContent

    static constraints = {
    }

    static mapping = {
        version false
    }

    def String toString() {
        return parentContent
    }
}
