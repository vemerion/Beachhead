package mod.vemerion.beachhead.capability;


import mod.vemerion.beachhead.Beachhead;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class BeachheadGameProvider implements ICapabilitySerializable<INBT>{

	private LazyOptional<BeachheadGame> instance = LazyOptional.of(Beachhead.BEACHHEADGAME_CAP::getDefaultInstance);

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		return Beachhead.BEACHHEADGAME_CAP.orEmpty(cap, instance);
	}

	@Override
	public INBT serializeNBT() {
		return Beachhead.BEACHHEADGAME_CAP.getStorage().writeNBT(Beachhead.BEACHHEADGAME_CAP, instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional cannot be empty!")), null);
	}

	@Override
	public void deserializeNBT(INBT nbt) {
		Beachhead.BEACHHEADGAME_CAP.getStorage().readNBT(Beachhead.BEACHHEADGAME_CAP, instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional cannot be empty!")), null, nbt);
	}
}
