public class Workbench extends ItemArray implements Inventory {
    private final OContainerWorkbench workbench;

    public Workbench(OContainerWorkbench block) {
        super((OInventoryCraftResult) block.b);
        workbench = block;
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported. Please use update(Player player)");
        // This is ugly but we kinda need it. :(
    }

    public void update(Player player) {
        workbench.a((OEntityPlayer) player.getUser());
    }

    @Override
    public String getName() {
        return container.getName();
    }

    @Override
    public void setName(String value) {
        container.setName(value);
    }
}
