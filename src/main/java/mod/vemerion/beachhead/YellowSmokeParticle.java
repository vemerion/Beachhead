package mod.vemerion.beachhead;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SmokeParticle;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.world.World;

public class YellowSmokeParticle extends SmokeParticle {
	protected YellowSmokeParticle(World world, double x, double y, double z, double speedX, double speedY, double speedZ, float particleScale,
			IAnimatedSprite sprite) {
		super(world, x, y, z, speedX, speedY, speedZ, particleScale, sprite);
		this.particleRed = rand.nextFloat() * 0.1f + 0.9f;
		this.particleGreen = rand.nextFloat() * 0.1f + 0.9f;
		this.particleBlue = rand.nextFloat() * 0.1f + 0.5f;
	}

	public static class Factory implements IParticleFactory<BasicParticleType> {
		private final IAnimatedSprite spriteSet;

		public Factory(IAnimatedSprite p_i50554_1_) {
			this.spriteSet = p_i50554_1_;
		}

		public Particle makeParticle(BasicParticleType typeIn, World worldIn, double x, double y, double z,
				double xSpeed, double ySpeed, double zSpeed) {
			return new YellowSmokeParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, 1, this.spriteSet);
		}
	}

}
