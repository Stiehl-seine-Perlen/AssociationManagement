-- Insert data into the Address table
INSERT INTO Address (adressId, city, country, houseNumber, postalCode, street)
VALUES
    (101, 'New York', 'USA', '123', '10001', 'Main Street'),
    (102, 'London', 'UK', '456', 'SW1A 1AA', 'Oxford Road'),
    (103, 'Paris', 'France', '789', '75001', 'Rue de la Paix');

-- Insert data into the Association table
INSERT INTO Association (id, associationName, businessMail, description, feeCollectionsPerYear, membershipFee, registerNumber, taxNumber, address_adressId)
VALUES
    (101, 'Association A', 'info@associationA.com', 'Association A Description', 4, 100.00, 'REG001', 'TAX001', 101),
    (102, 'Association B', 'info@associationB.com', 'Association B Description', 2, 50.00, 'REG002', 'TAX002', 102);

-- Insert data into the AssociationRole table
INSERT INTO AssociationRole (associationRoleId, canAccount, canEvent, canTransaction, roleDescription, roleName, association_id)
VALUES
    (1, true, true, false, 'Role Description 1', 'Role 1', 101),
    (2, false, true, true, 'Role Description 2', 'Role 2', 102);

-- Insert data into the Membership table
INSERT INTO Membership (membershipId, userId, association_id, associationRole_associationRoleId)
VALUES
    (1, 1001, 101, 1),
    (2, 1002, 102, 2);