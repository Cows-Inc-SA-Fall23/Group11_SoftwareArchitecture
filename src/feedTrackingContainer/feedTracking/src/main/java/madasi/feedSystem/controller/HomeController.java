package madasi.feedSystem.controller;

import java.util.Locale;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import madasi.feedSystem.model.Setting;
import madasi.feedSystem.service.SettingRepository;
import madasi.feedSystem.service.UserRepository;

@Controller
public class HomeController {
	
	@Autowired
    private MessageSource messageSource;

	Logger logger = LoggerFactory.getLogger(HomeController.class);

	String PROJECT_NAME = "Farmer Control Panel";

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SettingRepository settingRepository;

	// executes on startup
	@PostConstruct
	void onStartup() {
		try {
			logger.info("HELLO");
			Optional<Setting> s = settingRepository.findById(1);
			Setting setupSet;
			if (s.isEmpty()) {
				setupSet = new Setting();
				setupSet.setId(1);
				settingRepository.save(setupSet);
			} else {
				setupSet = s.get();
			}
			setupSet.setProjectName(PROJECT_NAME);
			settingRepository.save(s.get());
			logger.info(s.toString());

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
		String localeMessage = messageSource.getMessage("home.title", null, locale);

		String messages = "Hello";

		model.addAttribute("msg", messages);
		model.addAttribute("msg", localeMessage);

		return "home";
	}

	@RequestMapping("/demo")
	public String demo(Model model, HttpSession session) {
		return "demo";
	}
	
}