package madasi.controlPanel.controller;

import java.util.Locale;
import madasi.controlPanel.util.CustomUtil;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import madasi.controlPanel.model.Setting;
import madasi.controlPanel.service.SettingRepository;
import madasi.controlPanel.service.UserRepository;

@Controller
public class HomeController {
	
	@Autowired
    private MessageSource messageSource;

	Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Value("${project.name}")
	public String PROJECT_NAME;

	@Autowired
	private UserRepository userRepository;

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

	// if you want the home page to be something else.
//	@RequestMapping("/")

	@RequestMapping("/")
	public String home(Model model, HttpSession session, Locale locale) {
		String localeTitle = messageSource.getMessage("home.title", null, locale);
		model.addAttribute("title", localeTitle);

		return "home";
	}

	@RequestMapping("/demo")
	public String demo(Model model, HttpSession session) {
		return "demo";
	}
	
}