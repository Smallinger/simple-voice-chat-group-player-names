package de.smallinger.svcgroupplayernames;

import net.neoforged.neoforge.common.ModConfigSpec;

/**
 * Configuration for Simple Voice Chat Group Player Names
 * Original mod by Greenman999
 * NeoForge port by Smallinger
 */
public class ModConfig {

    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SPEC;

    public static final ModConfigSpec.BooleanValue ONLY_SHOW_NAMES_WHEN_TALKING;
    public static final ModConfigSpec.BooleanValue USE_TEXT_OUTLINE;
    public static final ModConfigSpec.IntValue TRANSPARENCY_WHEN_TALKING;
    public static final ModConfigSpec.IntValue TRANSPARENCY_WHEN_NOT_TALKING;


    static {
        ONLY_SHOW_NAMES_WHEN_TALKING = BUILDER
                .comment("Only show player names when they are talking")
                .translation("svcgroupplayernames.configuration.onlyShowNamesWhenTalking")
                .define("onlyShowNamesWhenTalking", false);

        USE_TEXT_OUTLINE = BUILDER
                .comment("Draw an outline around player names for better visibility")
                .translation("svcgroupplayernames.configuration.useTextOutline")
                .define("useTextOutline", false);

        TRANSPARENCY_WHEN_TALKING = BUILDER
                .comment("Transparency of player names when talking (0-100)")
                .translation("svcgroupplayernames.configuration.transparencyWhenTalking")
                .defineInRange("transparencyWhenTalking", 100, 0, 100);

        TRANSPARENCY_WHEN_NOT_TALKING = BUILDER
                .comment("Transparency of player names when not talking (0-100)")
                .translation("svcgroupplayernames.configuration.transparencyWhenNotTalking")
                .defineInRange("transparencyWhenNotTalking", 50, 0, 100);

        SPEC = BUILDER.build();
    }
}
