package servlets;

import com.google.gson.Gson;
import dto.PlayerDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.PlayerService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/get-players-servlet")
public class GetPlayersServlet extends HttpServlet {
    private PlayerService playerService;

    @Override
    public void init() throws ServletException {
        super.init();
        playerService = (PlayerService) getServletContext().getAttribute("playerService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        resp.setContentType("application/json");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        int game_id = (int) session.getAttribute("game_id");
        try (PrintWriter out = resp.getWriter()) {
            List<PlayerDTO> players = playerService.getAllPlayers(game_id);
            Gson gson = new Gson();
            String json = gson.toJson(players);
            out.print(json);
            out.flush();
        }
    }
}
