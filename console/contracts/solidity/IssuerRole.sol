pragma solidity ^0.4.25;

import "./LibRoles.sol";

contract IssuerRole {
    using LibRoles for LibRoles.Role;

    event IssuerAdded(address indexed account);
    event IssuerRemoved(address indexed account);

    LibRoles.Role private _issuers;

    constructor () internal {
        _issuers.add(msg.sender);
    }

    modifier onlyIssuer() {
        require(isIssuer(msg.sender), "IssuerRole: caller does not have the Issuer role");
        _;
    }

    function isIssuer(address account) public view returns (bool) {
        return _issuers.has(account);
    }

    function addIssuer(address account) public {
        _issuers.add(account);
        emit IssuerAdded(account);
    }

    function renounceIssuer(address account) public onlyIssuer {
        _issuers.remove(account);
        emit IssuerRemoved(account);
    }
}