package com.diamantino.stevevsalex.entities.base;

import com.diamantino.stevevsalex.entities.alex.AlexArrowEntity;
import com.diamantino.stevevsalex.entities.alex.AlexOmbEntity;
import com.diamantino.stevevsalex.entities.steve.SteveArrowEntity;
import com.diamantino.stevevsalex.entities.steve.SteveOmbEntity;
import com.diamantino.stevevsalex.registries.SVAEntityTypes;
import com.diamantino.stevevsalex.registries.SVAItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public enum Teams {
    ALEX,
    STEVE;

    public static BombEntity createBombForTeam(Teams team, Level level, int explosionPower) {
        switch (team) {
            case ALEX -> {
                return new AlexOmbEntity(SVAEntityTypes.ALEX_OMB.get(), level, explosionPower);
            }
            case STEVE -> {
                return new SteveOmbEntity(SVAEntityTypes.STEVE_OMB.get(), level, explosionPower);
            }
        }

        return null;
    }

    public static Item getBombItemForTeam(Teams team) {
        switch (team) {
            case ALEX -> {
                return SVAItems.ALEX_OMB_ITEM.get();
            }
            case STEVE -> {
                return SVAItems.STEVE_OMB_ITEM.get();
            }
        }

        return null;
    }

    public static Item getMAGItem(ProjectileType type, Teams team) {
        switch (team) {
            case STEVE -> {
                switch (type) {
                    case ARROW -> {
                        return SVAItems.STEVE_ARROW_MAG_ITEM.get();
                    }
                }
            }
            case ALEX -> {
                switch (type) {
                    case ARROW -> {
                        return SVAItems.ALEX_ARROW_MAG_ITEM.get();
                    }
                }
            }
        }

        return null;
    }

    public static ProjectileEntity createProjectileForTeam(Teams team, ProjectileType type, Level level, double x, double y, double z) {
        switch (team) {
            case ALEX -> {
                switch (type) {
                    case ARROW -> {
                        return new AlexArrowEntity(level, x, y, z);
                    }
                }
            }
            case STEVE -> {
                switch (type) {
                    case ARROW -> {
                        return new SteveArrowEntity(level, x, y, z);
                    }
                }
            }
        }

        return null;
    }
}
