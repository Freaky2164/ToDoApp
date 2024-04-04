package de.dhbw.ase.todoapp.domain.vo;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Objects;

import de.dhbw.ase.todoapp.domain.exceptions.InvalidPasswordException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;


@Embeddable
public final class Password
{
    @Column(name = "password", nullable = false)
    private final byte[] password;

    @Column(name = "salt", nullable = false)
    private final byte[] salt;

    protected Password()
    {
        // default constructor for JPA
        this.password = null;
        this.salt = null;
    }


    public Password(final String password)
    {
        Objects.requireNonNull(password);
        if (!isValid(password))
        {
            throw new InvalidPasswordException();
        }
        this.salt = generateSalt();
        this.password = hashPassword(password, salt);
    }


    public Password(final String password, final byte[] salt)
    {
        Objects.requireNonNull(password);
        Objects.requireNonNull(salt);
        if (!isValid(password))
        {
            throw new InvalidPasswordException();
        }
        this.salt = salt;
        this.password = hashPassword(password, salt);
    }


    private boolean isValid(String password)
    {
        return password.matches("[a-zA-Z0-9]*") && password.length() >= 8;
    }


    private byte[] hashPassword(String password, byte[] salt)
    {
        try
        {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.reset();
            digest.update(salt);
            byte[] hashedPassword = digest.digest(password.getBytes());
            return hashedPassword;
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            return new byte[0];
        }
    }


    private byte[] generateSalt()
    {
        byte[] salt = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        return salt;
    }


    public byte[] getPassword()
    {
        return password;
    }


    public byte[] getSalt()
    {
        return salt;
    }


    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(password);
        result = prime * result + Arrays.hashCode(salt);
        return result;
    }


    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Password other = (Password)obj;
        return Arrays.equals(password, other.password) && Arrays.equals(salt, other.salt);
    }
}
