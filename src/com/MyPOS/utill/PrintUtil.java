package com.MyPOS.utill;
import javafx.print.PageLayout;
import java.awt.print.PrinterException;
import javafx.print.PrinterJob;
import javafx.scene.Node; // Import Node for printable content
import javafx.stage.Stage;  // Import Stage for Window object
public class PrintUtil {
    public static void print(Node node, Stage primaryStage) {
        PrinterJob job = PrinterJob.createPrinterJob();

        if (job.showPageSetupDialog(primaryStage)) {
            //PageLayout pageLayout = job.getPrinterPageLayout();
            //job.setPageLayout(pageLayout);
            boolean success = job.printPage(node);
            if (success) {
                job.endJob();
            } else {
                System.out.println("Printing failed!");
            }
        }
    }
}
