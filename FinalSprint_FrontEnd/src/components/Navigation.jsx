import './Navigation.css'
import { Link } from "react-router-dom";


const Navigation = () => {
  return (
    <nav id="main-nav">
      <ul>
        <li>
          <Link to="/">Home</Link>
        </li>
        <li>
          <Link to="/enter-numbers">Add Binary Search Tree</Link>
        </li>
        <li>
          <Link to="/previous-trees">Show Previous Binary Search Trees</Link>
        </li>
      </ul>
    </nav>
  );
};
export default Navigation;