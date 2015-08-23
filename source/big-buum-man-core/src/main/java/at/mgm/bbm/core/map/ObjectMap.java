package at.mgm.bbm.core.map;

import at.mgm.bbm.core.events.EventExecutor;
import at.mgm.bbm.core.objects.DisplayObject;
import at.mgm.bbm.core.objects.fields.Field;
import at.mgm.bbm.core.objects.gameobjects.Bomb;
import at.mgm.bbm.core.objects.gameobjects.Player;

import java.util.*;

public enum ObjectMap {

    INSTANCE;

    public static final long UPDATE_INTERVAL = 20;

    ObjectMap() {
        this.displayObjectList = new ArrayList<DisplayObject>();
        this.playerList = new ArrayList<Player>();
    }

    private final List<DisplayObject> displayObjectList;
    private final List<Player> playerList;

    public synchronized int getObjectCount() {
        return this.displayObjectList.size();
    }

    public synchronized boolean addObject(final DisplayObject paramDisplayObject) {
        boolean success = false;
        if (null != paramDisplayObject) {
            if (paramDisplayObject instanceof Player) {
                this.playerList.add((Player)paramDisplayObject);
                success = true;
            } else if (!this.isTaken(paramDisplayObject.x, paramDisplayObject.y)) {
                this.displayObjectList.add(paramDisplayObject);
                success = true;
            }
        }
        return success;
    }

    public synchronized boolean addPlayer(final Player paramPlayer) {
        boolean success = false;
        if (null != paramPlayer) {
            this.playerList.add(paramPlayer);
            success = true;
        }
        return success;
    }

    public synchronized DisplayObject getObject(final int paramX, final int paramY) {
        for (final DisplayObject displayObject : this.displayObjectList) {
            if (displayObject.x == paramX && displayObject.y == paramY) {
                return displayObject;
            }
        }
        return null;
    }

    public synchronized boolean isTaken(final int paramX, final int paramY) {
        for (final DisplayObject displayObject : this.displayObjectList) {
            if (displayObject.x == paramX && displayObject.y == paramY) {
                return true;
            }
        }
        return false;
    }

    public synchronized void removeObject(final int paramX, final int paramY) {
        for (final Iterator<DisplayObject> objectIterator = this.displayObjectList.iterator(); objectIterator.hasNext();) {
            final DisplayObject displayObject = objectIterator.next();
            if (displayObject.x == paramX && displayObject.y == paramY) {
                this.displayObjectList.remove(displayObject);
            }
        }
    }

    public synchronized void removeObject(final DisplayObject paramDisplayObject) {
        if (null != paramDisplayObject && !(paramDisplayObject instanceof Player)) {
            this.displayObjectList.remove(paramDisplayObject);
        }
    }

    public synchronized void removePlayer(final Player paramPlayer) {
        if (null != paramPlayer) {
            this.playerList.remove(paramPlayer);
        }
    }

    public synchronized void movePlayer(final Player paramPlayer, final int paramHorizontalSteps, final int paramVerticalSteps) {
        final int newX = paramPlayer.x + paramHorizontalSteps;
        final int newY = paramPlayer.y + paramVerticalSteps;
        final Field field = VMap.INSTANCE.getField(newX, newY);
        if (null != field && field.acceptPlayer) {
            paramPlayer.x = newX;
            paramPlayer.y = newY;
            this.objectEvent(paramPlayer);
        }
    }

    private void objectEvent(final Player paramPlayer) {
        final int playerX = paramPlayer.x;
        final int playerY = paramPlayer.y;
        // handle player-object-collision
        final Map<Player, DisplayObject> events = new HashMap<Player, DisplayObject>();
        for (final Iterator<DisplayObject> objectIterator = displayObjectList.iterator(); objectIterator.hasNext();) {
            final DisplayObject displayObject = objectIterator.next();
            if (!(displayObject instanceof Bomb) && playerX == displayObject.x && playerY == displayObject.y) {
                events.put(paramPlayer, displayObject);
            }
        }
        // create events for each collision
        for (final Iterator iterator = events.entrySet().iterator(); iterator.hasNext();) {
            final Map.Entry pair = (Map.Entry)iterator.next();
            EventExecutor.INSTANCE.createEvent((Player)pair.getKey(), (DisplayObject)pair.getValue());
            iterator.remove();
        }
    }
}
