class BootStrap {
    
    def sailwaveImportService

    def init = { servletContext ->
        sailwaveImportService.importSeries(new File("misc/Onsdagssejladser2012.xml"))
    }
    def destroy = {
    }
}
