class CustomEscapeCodec{

    static encode = { string ->

        if(string instanceof String){
            string.trim()
                .replaceAll("&(?!amp;)", "&amp;")
                .replaceAll("\n","<br/>")
        }

    }
}