import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RulesCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2,
			String[] arg3) {
		
		if(arg3 != null && arg3.length == 1 && arg3[0].equals("reload") && arg0.isOp()){
			try {
				ASD.load();
				arg0.sendMessage("Rules successfully reloaded!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		for(String s : ASD.rule_lines){
			arg0.sendMessage(colorize(s));
			//arg0.sendMessage(s);
		}
		
		return true;
	}

	public static String colorize(String s){
        if(s == null) return null;
        s = s.replaceAll("&l", ChatColor.BOLD.toString());
        s = s.replaceAll("&n", ChatColor.UNDERLINE.toString());
        s = s.replaceAll("&o", ChatColor.ITALIC.toString());
        s = s.replaceAll("&k", ChatColor.MAGIC.toString());
        s = s.replaceAll("&m", ChatColor.STRIKETHROUGH.toString());
        s = s.replaceAll("&r", ChatColor.RESET.toString());
        
        return s.replaceAll("&([0-9a-f])", "\u00A7$1");
        
    }
	
}
