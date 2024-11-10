package servlets;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import dto.Player;
import dao.PlayerDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/get-players-servlet")
public class GetPlayersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        PlayerDAO playerDAO = new PlayerDAO();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        int game_id = (int) session.getAttribute("game_id");
        try (PrintWriter out = resp.getWriter()) {
            List<Player> players = playerDAO.getAllPlayers(game_id);
            Gson gson = new Gson();
            String json = gson.toJson(players);
            out.print(json);
            out.flush();
        }
    }
}
