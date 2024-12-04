package weblisteners;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import services.*;
import services.impl.*;

@WebListener
public class InitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        UserService userService = new UserServiceImpl();
        RoleService roleService = new RoleServiceImpl();
        QuizService quizService = new QuizServiceImpl();
        QuestionService questionService = new QuestionServiceImpl();
        AnswerService answerService = new AnswerServiceImpl();
        GameService gameService = new GameServiceImpl();
        PlayerService playerService = new PlayerServiceImpl();

        sce.getServletContext().setAttribute("userService", userService);
        sce.getServletContext().setAttribute("roleService", roleService);
        sce.getServletContext().setAttribute("quizService", quizService);
        sce.getServletContext().setAttribute("questionService", questionService);
        sce.getServletContext().setAttribute("answerService", answerService);
        sce.getServletContext().setAttribute("gameService", gameService);
        sce.getServletContext().setAttribute("playerService", playerService);

    }
}
