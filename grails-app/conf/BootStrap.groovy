class BootStrap {
    
    def sailwaveImportService

    def init = { servletContext ->
        sailwaveImportService.parse(new File("misc/Onsdagssejladser2012.xml"))
    }
    def destroy = {
    }
}
