import React, { useState } from 'react';
import './Collapsible.css';

const Collapsible = ({ title, children }) => {
  const [isOpen, setIsOpen] = useState(false);

  const toggle = () => {
    setIsOpen(!isOpen);
  };

  return (
    <div>
      <button type="button" className={`collapsible ${isOpen ? 'active' : ''}`} onClick={toggle}>
        {title}
      </button>
      <div className="content" style={{ display: isOpen ? 'block' : 'none' }}>
        {children}
      </div>
    </div>
  );
};

export default Collapsible;
