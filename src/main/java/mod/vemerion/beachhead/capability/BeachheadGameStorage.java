package mod.vemerion.beachhead.capability;

import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class BeachheadGameStorage implements IStorage<BeachheadGame> {

	@Override
	public INBT writeNBT(Capability<BeachheadGame> capability, BeachheadGame instance, Direction side) {
		return IntNBT.valueOf(instance.getScore());
		
	}

	@Override
	public void readNBT(Capability<BeachheadGame> capability, BeachheadGame instance, Direction side, INBT nbt) {
		instance.setScore(((IntNBT)nbt).getInt());
	}
}
