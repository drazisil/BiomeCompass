package com.drazisil.biomecompass.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class BiomeCompassCommand extends CommandBase {

    //private static final Logger logger = LogManager.getLogger("BiomeCompass");
    private World world = null;

    private String commandName = "biomeconpass";

    @Override
    public String getCommandName() {
        return commandName;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        if (sender instanceof EntityPlayer){
            return "/" + commandName + " <biome name>";
        } else {
            return commandName;
        }
    }

    @Override
    public List getCommandAliases() {
        return null;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] argString) {

        // TODO: Add console handling
        if (sender instanceof EntityPlayer){
            world = sender.getEntityWorld();

            ChunkCoordinates senderCoordinates = sender.getPlayerCoordinates();
            int senderX = senderCoordinates.posX;
            int senderZ = senderCoordinates.posZ;


            if (argString.length > 0) {
                // Was a requested biome name provided?

                    /* Some biomes have more then one word in the name.
                    Assume all arguments are part of the name
                     */
                String requestedBiomeName = StringUtils.join(argString, " ").toLowerCase();
                scanForBiomeMatch(sender, senderX, senderZ, requestedBiomeName);
            } else {
                // Echo the sender's current biome
                if (!world.isRemote) {
                    BiomeGenBase senderBiome = world.getBiomeGenForCoords(senderX, senderZ);
                    sender.addChatMessage(new ChatComponentText("You are in a " + senderBiome.biomeName + " " + senderX + "," + senderZ));
                }
            }
        } else {
            sender.addChatMessage(new ChatComponentText("This command can only be run as a player."));
        }
    }

    /**
     * Returns true if the given command sender is allowed to use this command.
     *
     * @param sender the ICommandSender that issued the command
     */
    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    /**
     * Adds the strings available in this command to the given list of tab completion options.
     *
     * @param sender the ICommandSender that issued the command
     * @param argString the arguments
     */
    @Override
    public List addTabCompletionOptions(ICommandSender sender, String[] argString) {
        return null;
    }

    /**
     * Return whether the specified command parameter index is a username parameter.
     *
     * @param argString the arguments
     * @param index the index of the argument to check
     */
    @Override
    public boolean isUsernameIndex(String[] argString, int index) {
        return false;
    }

    private void scanForBiomeMatch(ICommandSender sender, int centerX, int centerZ, String requestedBiomeName){
        int scanRadius = 25;
        int chunkSize = 16;
        for(int i=(centerX - (scanRadius * chunkSize)); i<(centerZ + (scanRadius * chunkSize)); i += chunkSize){
            for(int j=(centerZ - (scanRadius * chunkSize)); j<(centerZ + (scanRadius * chunkSize)); j += chunkSize){
                String biomeName = world.getBiomeGenForCoords(i, j).biomeName;
                if (biomeName.toLowerCase().equals(requestedBiomeName)) {
                    if (!world.isRemote) {
                        sender.addChatMessage(new ChatComponentText(i + "," + j + " = " + biomeName));
                    }
                    return;
                }
            }
        }
        //logger.info("A "+ requestedBiomeName + " biome was not located within the search radius.");
        ChatComponentText msg =new ChatComponentText("A "+ requestedBiomeName
                + " biome was not located within the search radius.");
        sender.addChatMessage(msg);

    }
}
