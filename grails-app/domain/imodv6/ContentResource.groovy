package imodv6

class ContentResource {
    String name
    ContentResourceType resourceType
    byte[] file
    String resourceURL
    String fileName
    String fileContentType
    String description

    static belongsTo = [content: Content]

    static constraints = {
        description nullable: true
        fileName nullable: true
        fileContentType nullable: true
        resourceURL nullable: true
        resourceType nullable: true
        file(size: 0..20000000, nullable: true)
    }

    static mapping = {
        version false
        description type: 'text'
    }

    def String toString() {
        return name
    }
}
