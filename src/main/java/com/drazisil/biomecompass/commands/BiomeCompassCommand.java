package com.drazisil.biomecompass.commands;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class BiomeCompassCommand implements ICommand {

    private static final Logger logger = LogManager.getLogger("BiomeCompass");
    private World world = null;
    private int chunkSize = 16;
    private int scanRadius = 25;

    @Override
    public String getCommandName() {
        return "biomecompass";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "biomecompass";
    }

    @Override
    public List getCommandAliases() {
        return null;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] argString) {

        // TODO: Add console handling
        world = sender.getEntityWorld();

        ChunkCoordinates senderChoords = sender.getPlayerCoordinates();
        int senderX = senderChoords.posX;
        int senderZ = senderChoords.posZ;


        if (argString.length > 0) {

                    /* Some biomes have more then one word in the name.
                    Assume all arguments are part of the name
                     */
            String requestedBiomeName = StringUtils.join(argString, " ").toLowerCase();
            scanForBiomeMatch(senderX, senderZ, requestedBiomeName);
        }

        BiomeGenBase senderBiome = world.getBiomeGenForCoords(senderX, senderZ);


        if (!world.isRemote) {
            sender.addChatMessage(new ChatComponentText("You are in a " + senderBiome.biomeName + " " + senderX + "," + senderZ));

        }
    }


    /**
     * Returns true if the given command sender is allowed to use this command.
     *
     * @param sender
     */
    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    /**
     * Adds the strings available in this command to the given list of tab completion options.
     *
     * @param sender
     * @param argString
     */
    @Override
    public List addTabCompletionOptions(ICommandSender sender, String[] argString) {
        return null;
    }

    /**
     * Return whether the specified command parameter index is a username parameter.
     *
     * @param argString
     * @param index
     */
    @Override
    public boolean isUsernameIndex(String[] argString, int index) {
        return false;
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     * <p/>
     * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
     * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
     * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
     * <tt>y.compareTo(x)</tt> throws an exception.)
     * <p/>
     * <p>The implementor must also ensure that the relation is transitive:
     * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
     * <tt>x.compareTo(z)&gt;0</tt>.
     * <p/>
     * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
     * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
     * all <tt>z</tt>.
     * <p/>
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
     * class that implements the <tt>Comparable</tt> interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     * <p/>
     * <p>In the foregoing description, the notation
     * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
     * <tt>0</tt>, or <tt>1</tt> according to whether the value of
     * <i>expression</i> is negative, zero or positive.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws ClassCastException if the specified object's type prevents it
     *                            from being compared to this object.
     */
    @Override
    public int compareTo(Object o) {
        return 0;
    }

    private void scanForBiomeMatch(int centerX, int centerZ, String requestedBiomeName){
        boolean success = false;
        for(int i=(centerX - (scanRadius*chunkSize)); i<(centerZ + (scanRadius*chunkSize)); i += chunkSize){
            for(int j=(centerZ - (scanRadius*chunkSize)); j<(centerZ + (scanRadius*chunkSize)); j += chunkSize){
                String biomeName = world.getBiomeGenForCoords(i, j).biomeName;
                if (biomeName.toLowerCase().equals(requestedBiomeName)) {
                    success = true;
                    logger.info(i + "," + j + " = " + biomeName);
                    return;
                }
            }
        }
        if (success == false){
            logger.info("A "+ requestedBiomeName + " biome was not located within the search radius.");
            return;

        }
    }

}
