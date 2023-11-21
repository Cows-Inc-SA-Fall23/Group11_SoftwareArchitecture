package madasi.labSystem.service;

import madasi.labSystem.model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public Report createReport(String reportText, Integer batchId) {
        Report report = new Report();
        report.setBatch_id(batchId);
        report.setDate(new Timestamp(System.currentTimeMillis()));
        report.setReport_text(reportText);
        return reportRepository.save(report);
    }
}