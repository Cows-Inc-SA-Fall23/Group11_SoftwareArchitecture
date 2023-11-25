package madasi.labSystem.controller;

import java.util.Locale;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import madasi.labSystem.model.Report;
import madasi.labSystem.model.Setting;
import madasi.labSystem.model.Silo;
import madasi.labSystem.service.SettingRepository;
import madasi.labSystem.service.SiloRepository;
import madasi.labSystem.service.ReportRepository;
import madasi.labSystem.service.ReportService;

@Controller
public class HomeController {

	@Autowired
	private MessageSource messageSource;

	Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Value("${project.name}")
	public String PROJECT_NAME;

	@Autowired
	private SiloRepository siloRepository;

	@Autowired
	private ReportRepository reportRepository;

	@Autowired
	private SettingRepository settingRepository;

	// executes on startup
	@PostConstruct
	void onStartup() {
		try {
			logger.info("HELLO");
			logger.info(PROJECT_NAME);
			Optional<Setting> s = settingRepository.findById(1);
			Setting setupSet;
			if (s.isEmpty()) {
				setupSet = new Setting();
				setupSet.setId(1);
				setupSet.setProjectName(PROJECT_NAME);
				settingRepository.save(setupSet);
			} else {
				setupSet = s.get();
			}
			setupSet.setProjectName(PROJECT_NAME);
			settingRepository.save(setupSet);
			logger.info(setupSet.toString());

		} catch (Exception e) {
			logger.error("On startup error: ", e);
		}
	}

	// automatically ads settings object to pages
	@ModelAttribute
	public void addAttributes(Model model) {
		Optional<Setting> s = settingRepository.findById(1);
		model.addAttribute("setting", s.get());
	}

	@RequestMapping("/")
	public String home(Model model, HttpSession session, Locale locale) {
		String localeTitle = messageSource.getMessage("home.title", null, locale);
		model.addAttribute("title", localeTitle);

		return "home";
	}

	@RequestMapping("/silos")
	public String silos(Model model, HttpSession session) {

		for (Silo s : siloRepository.findAll()) {
			logger.info(s.getName());
		}

		return "silos";
	}

	@Autowired
	private ReportService ReportService;

	@PostMapping("/generateReport")
	public String generateReport(@RequestParam String reportText, @RequestParam Integer batchId,
			RedirectAttributes redirectAttributes) {
		Report report = ReportService.createReport(reportText, batchId);
		redirectAttributes.addFlashAttribute("report", report);
		return "redirect:/home";
	}

	@GetMapping("/api/reports/{id}")
	@ResponseBody
	public ResponseEntity<Report> getReportById(@PathVariable Integer id) {
		return reportRepository.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
}