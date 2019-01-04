package ir.fassih.workshop.core.jwt;

import liquibase.change.custom.CustomTaskChange;
import liquibase.database.Database;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.CustomChangeException;
import liquibase.exception.DatabaseException;
import liquibase.exception.ValidationErrors;
import liquibase.resource.ResourceAccessor;

import java.io.ByteArrayInputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class RsaKeyGenerator implements CustomTaskChange {

    private KeyPair kp;

    public RsaKeyGenerator() {
        KeyPairGenerator kpg;
        try {
            kpg = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("unknown algorithm RSA", e);
        }
        kpg.initialize(2048);
        kp = kpg.generateKeyPair();
    }


    private ByteArrayInputStream getPublicKey() {
        return new ByteArrayInputStream(kp.getPublic().getEncoded());
    }

    private ByteArrayInputStream getPrivateKey() {
        return new ByteArrayInputStream(kp.getPrivate().getEncoded());
    }



    @Override
    public void execute(Database database) throws CustomChangeException {
        try {
            JdbcConnection dbConn = (JdbcConnection) database.getConnection();
            PreparedStatement stmt =
                    dbConn.prepareStatement("INSERT INTO CORE_RSA_KEY (PUBLIC_KEY, PRIVATE_KEY, CREATION_TIME) VALUES (?, ?, ?)");
            stmt.setBlob(1, getPublicKey());
            stmt.setBlob(2, getPrivateKey());
            stmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new CustomChangeException("bad sql exception", e);
        } catch (DatabaseException e) {
            throw new CustomChangeException("cannot insert database", e);
        }
    }

    @Override
    public String getConfirmationMessage() {
        return "generating rsa key";
    }

    @Override
    public void setUp() {
        // do nothing
    }

    @Override
    public void setFileOpener(ResourceAccessor resourceAccessor) {
        // do nothing
    }

    @Override
    public ValidationErrors validate(Database database) {
        return null;
    }

}
