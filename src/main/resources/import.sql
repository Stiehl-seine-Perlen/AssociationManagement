-- Insert data into Address table
INSERT INTO Address (adressId, city, country, houseNumber, postalCode, street)
VALUES
    (101, 'New York', 'USA', '123', '10001', 'Main Street'),
    (102, 'London', 'UK', '456', 'SW1A 1AA', 'Park Avenue');
--     (3, 'Paris', 'France', '789', '75001', 'Rue de la Paix'),
--     (4, 'Sydney', 'Australia', '321', '2000', 'King''s Road');

-- Insert data into the Association table
INSERT INTO Association (id, associationName, businessMail, description, feeCollectionsPerYear, membershipFee, registerNumber, taxNumber, address_adressId, logoName)
VALUES
    (101, 'Association A', 'info@associationA.com', 'Association A Description', 4, 100.00, 'REG001', 'TAX001', 101, 'defaultLogo.jpeg'),
    (102, 'Association B', 'info@associationB.com', 'Association B Description', 2, 50.00, 'REG002', 'TAX002', 102, 'defaultLogo.jpeg');

-- Insert data into the AssociationRole table
INSERT INTO AssociationRole (associationRoleId, canAccount, canEvent, canTransaction, roleDescription, roleName, association_id)
VALUES
    (1, true, false, true, 'Admin role', 'Admin', 101),
    (2, true, true, false, 'Member role', 'Member', 102);
--     (3, false, true, true, 'Guest role', 'Guest', 103),
--     (4, true, true, true, 'Superadmin role', 'Superadmin', 104);

-- Insert data into Membership table
INSERT INTO Membership (membershipId, userId, association_id, associationRole_associationRoleId)
VALUES
    (1, 123, 101, 1),
    (2, 456, 102, 2);
--     (3, 789, 103, 3),
--     (4, 987, 104, 4);
