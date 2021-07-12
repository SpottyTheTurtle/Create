package com.simibubi.create.content.curiosities.zapper;

import java.util.Vector;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.simibubi.create.foundation.gui.AbstractSimiScreen;
import com.simibubi.create.foundation.gui.AllGuiTextures;
import com.simibubi.create.foundation.gui.AllIcons;
import com.simibubi.create.foundation.gui.GuiGameElement;
import com.simibubi.create.foundation.gui.widgets.IconButton;
import com.simibubi.create.foundation.networking.AllPackets;
import com.simibubi.create.foundation.networking.NbtPacket;
import com.simibubi.create.foundation.utility.Lang;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class ZapperScreen extends AbstractSimiScreen {

	protected ItemStack zapper;
	protected boolean offhand;
	protected float animationProgress;
	protected AllGuiTextures background;
	private IconButton confirmButton;
	
	protected final ITextComponent patternSection = Lang.translate("gui.blockzapper.patternSection");

	protected ITextComponent title;
	protected Vector<IconButton> patternButtons;
	protected int brightColor;
	protected int fontColor;

	public ZapperScreen(AllGuiTextures background, ItemStack zapper, boolean offhand) {
		super();
		this.background = background;
		this.zapper = zapper;
		this.offhand = offhand;
		title = StringTextComponent.EMPTY;
		brightColor = 0xFEFEFE;
		fontColor = AllGuiTextures.FONT_COLOR;
	}

	@Override
	protected void init() {
<<<<<<< HEAD
		setWindowSize(background.width, background.height);
		setWindowOffset(-10, 0);
		super.init();
		widgets.clear();

		animationProgress = 0;

		int x = guiLeft;
		int y = guiTop;

		confirmButton =
			new IconButton(x + background.width - 33, y + background.height - 24, AllIcons.I_CONFIRM);
		widgets.add(confirmButton);

=======
		animationProgress = 0;
		setWindowSize(background.width + 40, background.height);
		super.init();
		widgets.clear();
		
		confirmButton = new IconButton(guiLeft + background.width - 53, guiTop + background.height - 24, AllIcons.I_CONFIRM);
		widgets.add(confirmButton);

		int i = guiLeft - 20;
		int j = guiTop;
>>>>>>> parent of 8fff3d674 (The great second purge of Tools)
		CompoundNBT nbt = zapper.getOrCreateTag();

		patternButtons = new Vector<>(6);
		for (int row = 0; row <= 1; row++) {
			for (int col = 0; col <= 2; col++) {
				int id = patternButtons.size();
				PlacementPatterns pattern = PlacementPatterns.values()[id];
<<<<<<< HEAD
				patternButtons
					.add(new IconButton(x + background.width - 76 + col * 18, y + 21 + row * 18, pattern.icon));
=======
				patternButtons.add(new IconButton(i + background.width - 76 + col * 18, j + 19 + row * 18, pattern.icon));
>>>>>>> parent of 8fff3d674 (The great second purge of Tools)
				patternButtons.get(id)
					.setToolTip(Lang.translate("gui.blockzapper.pattern." + pattern.translationKey));
			}
		}

		if (nbt.contains("Pattern"))
			patternButtons.get(PlacementPatterns.valueOf(nbt.getString("Pattern"))
				.ordinal()).active = false;

		widgets.addAll(patternButtons);
	}

	@Override
<<<<<<< HEAD
	protected void renderWindow(MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
		int x = guiLeft;
		int y = guiTop;
=======
	protected void renderWindow(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		int i = guiLeft - 20;
		int j = guiTop;
>>>>>>> parent of 8fff3d674 (The great second purge of Tools)

		background.draw(ms, this, x, y);
		drawOnBackground(ms, x, y);

		renderBlock(ms, x, y);
		renderZapper(ms, x, y);
	}

<<<<<<< HEAD
	protected void drawOnBackground(MatrixStack ms, int x, int y) {
		textRenderer.draw(ms, title, x + 11, y + 4, 0x54214F);
=======
	protected void drawOnBackground(MatrixStack matrixStack, int i, int j) {
		textRenderer.drawWithShadow(matrixStack, title, i + 11, j + 3, brightColor);
>>>>>>> parent of 8fff3d674 (The great second purge of Tools)
	}

	@Override
	public void tick() {
		super.tick();
		animationProgress += 5;
	}

	@Override
	public void removed() {
		CompoundNBT nbt = zapper.getTag();
		writeAdditionalOptions(nbt);
		AllPackets.channel.sendToServer(new NbtPacket(zapper, offhand ? Hand.OFF_HAND : Hand.MAIN_HAND));
	}

	@Override
	public boolean mouseClicked(double x, double y, int button) {
		CompoundNBT nbt = zapper.getTag();

		for (IconButton patternButton : patternButtons) {
			if (patternButton.isHovered()) {
				patternButtons.forEach(b -> b.active = true);
				patternButton.active = false;
				patternButton.playDownSound(client.getSoundHandler());
				nbt.putString("Pattern", PlacementPatterns.values()[patternButtons.indexOf(patternButton)].name());
			}
		}
		
		if (confirmButton.isHovered()) {
			onClose();
			return true;
		}

		return super.mouseClicked(x, y, button);
	}

	protected void renderZapper(MatrixStack ms, int x, int y) {
		GuiGameElement.of(zapper)
<<<<<<< HEAD
				.scale(4)
				.at(x + background.width, y + background.height - 48, -200)
				.render(ms);
	}

	protected void renderBlock(MatrixStack ms, int x, int y) {
		ms.push();
		ms.translate(x + 32, y + 42, 120);
		ms.multiply(new Vector3f(1f, 0, 0).getDegreesQuaternion(-25f));
		ms.multiply(new Vector3f(0, 1f, 0).getDegreesQuaternion(-45f));
		ms.scale(20, 20, 20);
=======
			.at((this.width - this.sWidth) / 2 + 200, this.height / 2 - this.sHeight / 4 + 25, -150)
			.scale(4)
			.render(matrixStack);
	}

	protected void renderBlock(MatrixStack matrixStack) {
		matrixStack.push();
		matrixStack.translate(guiLeft + 7f, guiTop + 43.5f, 120);
		matrixStack.multiply(new Vector3f(.5f, .9f, -.1f).getDegreesQuaternion(-30f));
		matrixStack.scale(20, 20, 20);
>>>>>>> parent of 8fff3d674 (The great second purge of Tools)

		BlockState state = Blocks.AIR.getDefaultState();
		if (zapper.hasTag() && zapper.getTag()
			.contains("BlockUsed"))
			state = NBTUtil.readBlockState(zapper.getTag()
				.getCompound("BlockUsed"));

		GuiGameElement.of(state)
			.render(ms);
		ms.pop();
	}

	protected void writeAdditionalOptions(CompoundNBT nbt) {}

}
