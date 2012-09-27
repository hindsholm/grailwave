class BootStrap {
    
    def sailwaveImportService

    def init = { servletContext ->
        sailwaveImportService.importSeries(new File("misc/TestCase.xml"))
    }
    def destroy = {
    }
}
