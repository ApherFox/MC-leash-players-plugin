package net.apherfox.random-proj.events;

import main.Pair;

public class EntityDamage implements Listener {
  @EventHandler
  public void onEntityDamage(EntityDamageEvent event) {
    for (Pair pair : Pair.pairs) {
      if (pair.online) {
        Entity entity = event.getEntity();
        if (entity.getType() == EntityType.PLAYER) {
          if ((event.getCause() == EntityDamageEvent.DamageCause.FALL) && (pair.submissive.equals((Player)entity))) {
            event.setCancelled(true);
          }
        }
      }
    }
  }
}
