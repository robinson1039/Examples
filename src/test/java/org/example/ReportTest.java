package org.example;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportTest {
    private static ExtentReports extent;

    // Método para configurar el reportador
    private static ExtentReports extentReporterConfig() {
        if (extent == null) {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("Report/RegistrarUsuarioReport.html");
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
        }
        return extent;
    }

    // Método para obtener la instancia del reportador
    public static ExtentReports getExtentReports() {
        return extentReporterConfig();
    }

    // Método para cerrar el reporte al finalizar las pruebas
    public static void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }
}
