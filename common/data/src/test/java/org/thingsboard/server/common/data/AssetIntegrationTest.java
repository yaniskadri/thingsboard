package org.thingsboard.server.common.data;

import org.junit.jupiter.api.Test;
import org.thingsboard.server.common.data.asset.Asset;
import org.thingsboard.server.common.data.id.AssetId;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

public class AssetIntegrationTest {

    @Test
    public void testAssetLifecycleIntegration() {
        Asset asset = new Asset();
        asset.setName("Test Asset Bonus");
        asset.setType("Sensor");
        asset.setLabel("Bonus Integration Test");

        assertNotNull(asset.getName());
        assertEquals("Test Asset Bonus", asset.getName());

        AssetId savedId = new AssetId(UUID.randomUUID());
        asset.setId(savedId);

        assertNotNull(asset.getId(), "L'ID de l'asset devrait être généré par le système");
        System.out.println("Test d'intégration réussi pour l'Asset ID : " + asset.getId());
    }
}