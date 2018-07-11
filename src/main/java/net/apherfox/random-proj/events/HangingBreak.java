package net.apherfox.random-proj.events;

import main.Pair;

public class HangingBreak implements Listener {
  @EventHandler
  public void onHangingBreak(HangingBreakEvent event) {
    for (Pair pair : Pair.pairs) {
      if ((pair.bat != null) && (event.getEntity().equals(pair.bat))) {
        event.setCancelled(true);
      }
    }
  }
}
